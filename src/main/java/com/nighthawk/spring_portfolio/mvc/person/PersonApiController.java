package com.nighthawk.spring_portfolio.mvc.person;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/person")
public class PersonApiController {
    //     @Autowired
    // private JwtTokenUtil jwtGen;
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private PersonJpaRepository repository;

    @Autowired
    private PersonDetailsService personDetailsService;

    /*
    GET List of People
     */
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Person>> getPeople() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    @GetMapping("/jwt")
    @PreAuthorize("isAuthenticated()")  // Restrict access to authenticated users
    public ResponseEntity<Person> getAuthenticatedPersonData() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);  // Retrieve data for the authenticated user
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /*
    GET individual Person using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID
            return new ResponseEntity<>(person, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/leaderboardCSP")
    public List<Person> getLeaderboardCSP() {
        // Get top 5 users based on cspPoints
        return repository.findTop5ByOrderByCspPointsDesc();
    }

    @GetMapping("/leaderboardCSA")
    public List<Person> getLeaderboardCSA() {
        // Get top 5 users based on cspPoints
        return repository.findTop5ByOrderByCsaPointsDesc();
    }

    /*
    DELETE individual Person using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(person, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping("/post")
    public ResponseEntity<Object> postPerson(@RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("name") String name) {
    
        // Check if a person with the same name already exists
        Person existingPerson = personDetailsService.getByName(name);
        if (existingPerson != null) {
            return new ResponseEntity<>("User with name " + name + " already exists", HttpStatus.BAD_REQUEST);
        }

        // Check if a person with the same email already exists
        Person existingPersonByEmail = personDetailsService.getByEmail(email);
        if (existingPersonByEmail != null) {
            return new ResponseEntity<>("User with email " + email + " already exists", HttpStatus.BAD_REQUEST);
        }
    
        // If no existing person with the same name, create and save the new person
        int csaPoints = 0;
        int cspPoints = 0;
        int profilePicInt = 0;
        int accountPoints = 0;
        int accountLevel = 1;
        int weaponGearIdEquipped = 0;
        int armorGearIdEquipped = 0;
        int accessoryGearIdEquipped = 0;
        List<Integer> inventory = null;
        // base health, gear health, base attack, gear attack
        int[][] statsArray = {{100, 0}, {100, 0}};
        
        Person person = new Person(email, password, name, csaPoints, cspPoints, profilePicInt, accountPoints, accountLevel, statsArray, inventory, weaponGearIdEquipped, armorGearIdEquipped, accessoryGearIdEquipped);
        personDetailsService.save(person);
    
        return new ResponseEntity<>(email + " is created successfully", HttpStatus.CREATED);
    }

    @PostMapping("appendInventory")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> appendInventory(@RequestParam("gearID") int gearID) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);
        List<Integer> inventory = person.getInventory();
        if (inventory.contains(gearID)) {
            return new ResponseEntity<>("Gear with ID of " + gearID + " is already in this account's inventory", HttpStatus.BAD_REQUEST);
        }
        inventory.add(gearID);
        person.setInventory(inventory);
        repository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    

    /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> personSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Person> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*
    The personStats API adds stats by Date to Person table 
    */
    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personStats(@RequestBody final Map<String,Object> stat_map) {
        // find ID
        long id=Long.parseLong((String)stat_map.get("id"));  
        Optional<Person> optional = repository.findById((id));
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID

            // Extract Attributes from JSON
            Map<String, Object> attributeMap = new HashMap<>();
            for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
                // Add all attribute other thaN "date" to the "attribute_map"
                if (!entry.getKey().equals("date") && !entry.getKey().equals("id"))
                    attributeMap.put(entry.getKey(), entry.getValue());
            }

            // Set Date and Attributes to SQL HashMap
            Map<String, Map<String, Object>> date_map = new HashMap<>();
            date_map.put( (String) stat_map.get("date"), attributeMap );
            person.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
            repository.save(person);  // conclude by writing the stats updates

            // return Person with update Stats
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        // return Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
    
    

    @PostMapping("/addPointsCSA")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Person> addPointsCSA(@RequestParam("points") int points) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);  // Retrieve data for the authenticated user
        person.setCsaPoints(person.getCsaPoints() + points);
        int accountPointsToBeSet = person.getAccountPoints() + points;
        person.setAccountPoints(accountPointsToBeSet);

        int[] levels = {0, 100, 221, 354, 500, 661, 839, 1034, 1248, 1485, 1746, 2031, 2345, 2690, 3069, 3483, 3937, 4438, 4994, 5615, 6301, 7064, 7910, 8857, 9914, 11098, 12389, 13800, 15343, 17029};

        int newLevel = 0; // Default level

        for (int i = 0; i < levels.length; i++) {
            if (accountPointsToBeSet >= levels[i]) {
                newLevel = i + 1; // Increment level if points meet or exceed the level threshold
            } else {
                break; // Break the loop once the current level is determined
            }
        }
        
        // If points exceed all levels, set the highest level
        if (accountPointsToBeSet > levels[levels.length - 1]) {
            newLevel = levels.length;
        }

        person.setAccountLevel(newLevel);

        repository.save(person);  // Save the updated Person object
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/addPointsCSP")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Person> addPointsCSP(@RequestParam("points") int points) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);  // Retrieve data for the authenticated user
        person.setCspPoints(person.getCspPoints() + points);
        int accountPointsToBeSet = person.getAccountPoints() + points;
        person.setAccountPoints(accountPointsToBeSet);

        int[] levels = {0, 100, 221, 354, 500, 661, 839, 1034, 1248, 1485, 1746, 2031, 2345, 2690, 3069, 3483, 3937, 4438, 4994, 5615, 6301, 7064, 7910, 8857, 9914, 11098, 12389, 13800, 15343, 17029};

        int newLevel = 0; // Default level

        for (int i = 0; i < levels.length; i++) {
            if (accountPointsToBeSet >= levels[i]) {
                newLevel = i + 1; // Increment level if points meet or exceed the level threshold
            } else {
                break; // Break the loop once the current level is determined
            }
        }
        
        // If points exceed all levels, set the highest level
        if (accountPointsToBeSet > levels[levels.length - 1]) {
            newLevel = levels.length;
        }

        person.setAccountLevel(newLevel);

        repository.save(person);  // Save the updated Person object
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/changeProfilePic")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Person> changeProfilePic(@RequestParam("profilePicInt") int profilePicInt) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);
        person.setProfilePicInt(profilePicInt);
        repository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/unequipArmor")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> unequipArmor(@RequestParam("armorID") int armorID) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);
        int[][] statsArray = person.getStatsArray();

        int gearHealthAdded = 0;
        int gearDamageAdded = 0;
  
        int currentArmor = person.getArmorGearIdEquipped();

        if ( currentArmor == 0) {
            return new ResponseEntity<>("No armor equipped", HttpStatus.BAD_REQUEST);
        }

        if ( armorID < 1000 || armorID > 2000) {
            return new ResponseEntity<>("ID does not match an armor item id range of 1000 to 1999", HttpStatus.BAD_REQUEST);
        }
        
        if (currentArmor != armorID) {
            return new ResponseEntity<>("Armor equipped does match id of " + armorID + " which is being requested", HttpStatus.BAD_REQUEST);
        }


        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("gear.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray gearArray = (JSONArray) jsonObject.get("items"); // Correct field name

            for (int i = 0; i < gearArray.size(); i++) {
                JSONObject gear = (JSONObject) gearArray.get(i);
                // Use the correct field name for gearID/id
                if (armorID == (int) (long) gear.get("gearID") || armorID == (int) (long) gear.get("id")) {
                    gearHealthAdded = (int) (long) gear.get("healthAdded"); // Correct field name
                    gearDamageAdded = (int) (long) gear.get("damageAdded"); // Correct field name
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        statsArray[0][1] -= gearHealthAdded;
        statsArray[1][1] -= gearDamageAdded;

        person.setStatsArray(statsArray);
        person.setArmorGearIdEquipped(0);
        repository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/equipArmor")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> equipArmor(@RequestParam("armorID") int armorID) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(username);
        int[][] statsArray = person.getStatsArray();

        int gearHealthAdded = 0;
        int gearDamageAdded = 0;
  
        int currentArmor = person.getArmorGearIdEquipped();

        List<Integer> inventory = person.getInventory();
        if (!inventory.contains(armorID)) {
            return new ResponseEntity<>("Gear with ID of " + armorID + " is not in this account's inventory", HttpStatus.BAD_REQUEST);
        }

        if (currentArmor >= 1000 && currentArmor < 2000) {
            return new ResponseEntity<>("Armor is already equipped", HttpStatus.BAD_REQUEST);
        }

        if ( armorID < 999 || armorID > 1999) {
            return new ResponseEntity<>("ID does not match an armor item id range of 1000 to 1999", HttpStatus.BAD_REQUEST);
        }

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("gear.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray gearArray = (JSONArray) jsonObject.get("items"); // Correct field name

            for (int i = 0; i < gearArray.size(); i++) {
                JSONObject gear = (JSONObject) gearArray.get(i);
                // Use the correct field name for gearID/id
                if (armorID == (int) (long) gear.get("gearID") || armorID == (int) (long) gear.get("id")) {
                    gearHealthAdded = (int) (long) gear.get("healthAdded"); // Correct field name
                    gearDamageAdded = (int) (long) gear.get("damageAdded"); // Correct field name
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        statsArray[0][1] += gearHealthAdded;
        statsArray[1][1] += gearDamageAdded;

        person.setStatsArray(statsArray);
        person.setArmorGearIdEquipped(armorID);
        repository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    // @PostMapping("/changeGear")
    // @PreAuthorize("isAuthenticated()")
    // public ResponseEntity<Person> changeGear(@RequestParam("equip") boolean equip,
    //                                          @RequestParam("gearID") int gearID){
    //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
    //     Person person = repository.findByEmail(username);
    //     int[][] statsArray = person.getStatsArray();

    //     int gearHealthAdded = 0;
    //     int gearDamageAdded = 0;

    //     JSONParser parser = new JSONParser();
    //     try {
    //         Object obj = parser.parse(new FileReader("gear.json"));
    //         JSONObject jsonObject = (JSONObject)obj;
    //         JSONArray gearArray = (JSONArray) jsonObject.get("items"); // Correct field name

    //         for (int i = 0; i < gearArray.size(); i++) {
    //             JSONObject gear = (JSONObject) gearArray.get(i);
    //             // Use the correct field name for gearID/id
    //             if (gearID == (int) (long) gear.get("gearID") || gearID == (int) (long) gear.get("id")) {
    //                 gearHealthAdded = (int) (long) gear.get("healthAdded"); // Correct field name
    //                 gearDamageAdded = (int) (long) gear.get("damageAdded"); // Correct field name
    //                 break;
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     if (equip) {
    //         statsArray[0][1] += gearHealthAdded;
    //         statsArray[1][1] += gearDamageAdded;
    //     } else {
    //         statsArray[0][1] -= gearHealthAdded;
    //         statsArray[1][1] -= gearDamageAdded;
    //     }

    //     person.setStatsArray(statsArray);
    //     repository.save(person);
    //     return new ResponseEntity<>(person, HttpStatus.OK);
    // }

}

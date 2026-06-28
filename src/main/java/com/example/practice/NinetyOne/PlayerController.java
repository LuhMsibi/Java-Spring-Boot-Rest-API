package com.example.practice.NinetyOne;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getTopPlayers(){
        return playerService.getTopPlayers();
    }

    @PostMapping
    public void addNewPlayer(@RequestBody Player player){
        playerService.addNewPlayer(player);
    }

    @GetMapping("search")
    public List<Player>getPayerByName(@RequestParam String firstName){
        return playerService.getPlayerByName(firstName);
    }

    //deleting a player
    @DeleteMapping("{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long playerId){
        playerService.deletePlayer(playerId);
    }

    // updating a player
    @PutMapping("{playerId}")
    public void updatePlayer(@PathVariable("playerId") Long playerId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String secondName){
        playerService.updatePlayer(playerId, firstName, secondName);

    }



}

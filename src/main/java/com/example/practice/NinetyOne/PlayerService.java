package com.example.practice.NinetyOne;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> readFromCsv(){
        List<Player> players = new ArrayList<>();

        String file = "src/main/resources/data/TestData.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine(); //skip header

            while ((line = reader.readLine()) != null){
                try {
                    String[] data = line.split(",");

                    String firstName = data[0];
                    String secondName = data[1];
                    int score = Integer.parseInt(data[2]);


                    Player player = new Player(firstName, secondName, score);
                    players.add(player);
                    playerRepository.save(player);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return players;

    }

    public List<Player> getTopPlayers(){
        List<Player> players = readFromCsv();

        int max = 0;
        for(Player p : players){
            if(p.getScore() > max){
                max = p.getScore();
            }
        }

        List<Player> topPlayers = new ArrayList<>();

        for (Player p : players){
            if(p.getScore() == max){
                topPlayers.add(p);
            }
        }
        return topPlayers;
    }


    /// adding a new player
    public void addNewPlayer(Player player){
        playerRepository.save(player);
    }

    public List<Player> getPlayerByName(String firstName){
        Optional<Player> playerName = playerRepository.findPlayerByFirstName(firstName);
        return List.of(playerName.orElseThrow(()-> new IllegalStateException("That player name does not exist")));
    }

    public void deletePlayer(Long playerId) {
        boolean exists = playerRepository.existsById(playerId);

        if(!exists){
            throw new IllegalStateException("The Id: " + playerId + " does not exist");
        }
        playerRepository.deleteById(playerId);
    }
    // update players infor

    @Transactional
    public void updatePlayer(Long playerId, String firstName, String secondName) {
        boolean exists = playerRepository.existsById(playerId);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()-> new IllegalStateException("Player with this Id does not exist"));

        if(!exists){
            throw new IllegalStateException("The Id: \" + playerId + \" does not exist");
        }
        if(firstName != null && firstName.length() > 0 && !Objects.equals(player.getFirstName(), firstName)){
            player.setFirstName(firstName);
        }
        if(secondName != null && secondName.length() > 0 && !Objects.equals(player.getSecondName(), secondName)){
            player.setSecondName(secondName);
        }

    }
}

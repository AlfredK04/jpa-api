package com.booleanuk.api.service;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    public Optional<Game> getById(Integer id) {
        return this.gameRepository.findById(id);
    }

    public Game create(Game game) {
        return this.gameRepository.save(game);
    }

    public Optional<Game> update(Integer id, Game replacement) {
        return this.gameRepository.findById(id)
                .map(game -> {
                    game.setTitle(replacement.getTitle());
                    game.setGenre(replacement.getGenre());
                    game.setPublisher(replacement.getPublisher());
                    game.setDeveloper(replacement.getDeveloper());
                    game.setReleaseYear(replacement.getReleaseYear());
                    game.setEarlyAccess(replacement.isEarlyAccess());
                    return this.gameRepository.save(game);
                });
    }

    public Optional<Game> delete(Integer id) {
        Optional<Game> game = this.gameRepository.findById(id);
        if (game.isPresent()) {
            this.gameRepository.delete(game.get());
        }
        return game;
    }
}
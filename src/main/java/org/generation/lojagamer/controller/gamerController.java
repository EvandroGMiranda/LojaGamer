package org.generation.lojagamer.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.generation.lojagamer.Model.gamerModel;
import org.generation.lojagamer.Repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/gamer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class gamerController {
	@Autowired
	private GamerRepository repository;

	@GetMapping
	public ResponseEntity<List<gamerModel>>getall() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{Id}")
	public ResponseEntity<gamerModel> getByID(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<gamerModel>> getByTitulo(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<gamerModel> Post(@Valid @RequestBody gamerModel postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<gamerModel> put(@Valid @RequestBody gamerModel postagem) {
		return repository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delet(@PathVariable Long id) {
		Optional<gamerModel> postagem = repository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);
	}
}

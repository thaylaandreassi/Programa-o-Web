package school.sptech.atividadecarrosprint2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.atividadecarrosprint2.dominio.Carro;
import school.sptech.atividadecarrosprint2.repository.CarroRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public ResponseEntity<Carro> criar(@RequestBody Carro novoCarro){
        Carro newCarro = carroRepository.save(novoCarro);
        if(newCarro != null){
            return ResponseEntity.status(201).body(newCarro);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listar(){
        List<Carro> carros = carroRepository.findAll();
        if(carros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(carros);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Carro> listarPorId(@PathVariable Integer id){
        Optional<Carro> carros = carroRepository.findById(id);
        if(carros.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(carros.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if(carroRepository.existsById(id)){
            carroRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return  ResponseEntity.status(404).build();
    }

}

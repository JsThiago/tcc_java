package com.ufsj.projetovaca.financeiro.apresentationLayer.controllers.tipoConta;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaOutput;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.CadastroTipoContaService;
import com.ufsj.projetovaca.financeiro.applicationLayer.applicationService.exceptions.NotFound;
@RestController()
@RequestMapping("tipoConta")
public class TipoContaController {
	
	@Autowired
	CadastroTipoContaService cadastroTipoContaService;
	
	@PostMapping()
	public ResponseEntity<?> criar(@RequestBody TipoContaInput tipoContaInput){
		try {
			
			
			TipoContaOutput tipoContaOutput = cadastroTipoContaService.criar(tipoContaInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoContaOutput);
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}	
	}
	@GetMapping()
	public ResponseEntity<?> listar(){
		try {
			
			List<TipoContaOutput> tiposContaOutput = cadastroTipoContaService.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(tiposContaOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}	
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody TipoContaInput contaInput,@PathVariable Long id){
		try {
			
			TipoContaOutput tipoContaOutput = cadastroTipoContaService.atualizar(contaInput, id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoContaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> encontrar(@PathVariable Long id){
		try {
			
			TipoContaOutput tipoContaOutput = cadastroTipoContaService.encontrar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoContaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			TipoContaOutput tipoContaOutput = cadastroTipoContaService.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(tipoContaOutput);
			
			
		}catch(NotFound e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
	}
}

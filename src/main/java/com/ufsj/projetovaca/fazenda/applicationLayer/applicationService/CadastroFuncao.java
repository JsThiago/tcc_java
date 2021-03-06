package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.domainLayer.domainServices.PodeDeletarComprador;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.FuncaoHasFuncionario;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.FuncaoAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.domainServices.PodeDeletarFuncao;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncaoRepository;
@Service
public class CadastroFuncao {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	@Autowired
	FuncaoAssembler funcaoAssembler;
	
	@Autowired
	PodeDeletarFuncao podeDeletarFuncao;
	
	public FuncaoOutput salvar(FuncaoInput funcaoInput) {
		Funcao funcao = funcaoAssembler.converterEntidade(funcaoInput);
		
		funcao.ativarFuncao();
		
		System.out.println(funcao);
		
		Funcao novaFuncao = funcaoRepository.save(funcao);
		
		System.out.println(novaFuncao);
		
		FuncaoOutput funcaoOutput = funcaoAssembler.converterOutput(novaFuncao);
		
		return funcaoOutput;
	}
	
	public FuncaoOutput desativar(long id) throws NotFoundWithId {
		Optional<Funcao> opFuncao = funcaoRepository.findById(id);
		
		if(opFuncao.isEmpty()) {
			throw new NotFoundWithId("Não encontrada função com esse id");
		}
		
		Funcao funcao = opFuncao.get();
		
		funcao.setAtivado(!funcao.isAtivado());
		
		FuncaoOutput funcaoOutput = funcaoAssembler.converterOutput(funcaoRepository.save(funcao));
		
		return funcaoOutput;
	}
	public FuncaoOutput deletar(long id) throws NotFoundWithId, FuncaoHasFuncionario {
		Optional<Funcao> opFuncao = funcaoRepository.findById(id);
		
		if(opFuncao.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrada um função com o id informado.");
		}
		
		if(!podeDeletarFuncao.execute(id)) {
			
			throw new FuncaoHasFuncionario("Foram encontrados funcionários que exercem essa função.");
		}
		
		Funcao funcao = opFuncao.get();
		
		funcaoRepository.delete(funcao);
		
		FuncaoOutput funcaoOutput = funcaoAssembler.converterOutput(funcao);
		
		return funcaoOutput;
	}
	
	public FuncaoOutput atualizar(long id,FuncaoInput funcaoInput) throws NotFoundWithId {
		System.out.println(id);
		
		Optional<Funcao> opFuncao = funcaoRepository.findById(id);
		
		if(opFuncao.isEmpty()) {
			throw new NotFoundWithId("Não encontrada função com esse id");
		}
		
		Funcao novaFuncao = funcaoAssembler.converterEntidade(funcaoInput);
		
		Funcao funcao = opFuncao.get();
		
		BeanUtils.copyProperties(novaFuncao,funcao,"id","ativado");
					
		funcao.setId(id);
		
		FuncaoOutput funcaoOutput = funcaoAssembler.converterOutput(funcaoRepository.save(funcao));
		
		return funcaoOutput;
	}
	public List<FuncaoOutput> listar() {
		
		List<Funcao> funcoes = funcaoRepository.findAll();
		
		List<FuncaoOutput> funcoesOutput = funcaoAssembler.converterColecaoOutput(funcoes);
		
		return funcoesOutput;
		
	}
}

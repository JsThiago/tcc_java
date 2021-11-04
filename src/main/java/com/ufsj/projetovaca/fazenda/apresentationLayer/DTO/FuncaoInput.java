package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Entrada de função",description = "Modelo que demonstra a entrada dos endpoints de função.")
public class FuncaoInput {
	@ApiModelProperty(value = "Nome da função que será realizada pelo funcionário.",example = "cuidador de gado",required = true)
	private String nome;
	@ApiModelProperty(value = "Valor do salário que o funcionário irá receber por realizar a função.",example = "3000.00",required = true)
	private float salario;
	@ApiModelProperty(value = "Campo que informa se a função ainda existe no sistema.",example = "false",required = true)
	private boolean isAtivado;
}

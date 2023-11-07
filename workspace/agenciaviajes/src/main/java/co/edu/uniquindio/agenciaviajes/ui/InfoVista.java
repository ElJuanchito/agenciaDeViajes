package co.edu.uniquindio.agenciaviajes.ui;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class InfoVista {
	@EqualsAndHashCode.Include
	OrgVista orgVista;
	@EqualsAndHashCode.Include
	TipoVista tipoVista;
	@EqualsAndHashCode.Include
	Object dato;
}

package io.github.jokoframework.porandu.manager;


import io.github.jokoframework.porandu.enums.TipoParametroEnum;

public interface ParametroManager {

	public ParametroManager reload();
	
	public Long getLong(String name);

	public ParametroManager saveNew(String nombre, String valor, TipoParametroEnum tipo);

	public ParametroManager update(String nombre, String valor);

	public ParametroManager remove(String nombre);
	
}

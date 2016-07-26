package io.github.jokoframework.porandu.manager;


import io.github.jokoframework.porandu.enums.TipoParametroEnum;

public interface ParametroManager {

	ParametroManager reload();
	
	Long getLong(String name);

	ParametroManager saveNew(String nombre, String valor, TipoParametroEnum tipo);

	ParametroManager update(String nombre, String valor);

	ParametroManager remove(String nombre);

    long count();
}

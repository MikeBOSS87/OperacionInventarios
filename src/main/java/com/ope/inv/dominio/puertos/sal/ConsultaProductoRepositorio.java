package com.ope.inv.dominio.puertos.sal;

import java.util.List;
import java.util.Map;

public interface ConsultaProductoRepositorio {
	
	Map<String, Integer> consultarPorSku( List<String> skus );
}
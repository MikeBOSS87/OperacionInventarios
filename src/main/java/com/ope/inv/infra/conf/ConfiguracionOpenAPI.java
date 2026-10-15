package com.ope.inv.infra.conf;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class ConfiguracionOpenAPI {
	
	@Bean OpenAPI operacionInventarioOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                    .title("API OperacionInventario")
                    .version("1.3.0")
                    .description("API para registrar movimientos de inventario, consolidar detalles por SKU y tipo, consultar el catálogo y reportar los SKU no catalogados."));
    }
}
package com.cat.inv.modelo;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class PeticionCredenciales {
	
	@Schema(description = "Token de acceso", example = "MDRG2501")
	@NotBlank(message = "El token es obligatorio")
    private String token;

	@Schema(description = "NIP de validación", example = "0609")
	@NotBlank(message = "El NIP es obligatorio")
    private String nip;

    public String getToken() {
        return token;
    }

    public String getNip() {
        return nip;
    }
}
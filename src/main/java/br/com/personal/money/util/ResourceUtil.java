package br.com.personal.money.util;

import org.springframework.http.ResponseEntity;

import br.com.personal.money.model.BasicEntity;

public final class ResourceUtil {
	
	public static ResponseEntity<?> getResponseOkOrNotFound(BasicEntity entity) {
		
		if(entity == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(entity);
	}

}

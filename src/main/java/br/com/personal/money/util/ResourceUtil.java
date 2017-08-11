package br.com.personal.money.util;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.personal.money.model.BasicEntity;

public final class ResourceUtil {
	
	public static ResponseEntity<?> getResponseOkOrNotFound(BasicEntity entity) {
		
		if(entity == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(entity);
	}

	public static ResponseEntity<?> getResponseOkOrNotFound(List<? extends BasicEntity> entities) {
		
		if(entities == null || entities.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(entities);
	}

}

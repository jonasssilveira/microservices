package br.com.ecommerce.superstore.usuario.usecase.interfaces.queue;

import br.com.ecommerce.superstore.usuario.domain.entity.dto.EmailDTO;

public interface Kafka {
    void send(EmailDTO emailDTO);
}

package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

	@Query(value="SELECT * FROM Message m WHERE  m.conv_id=:id AND m.message_visibility=:test order by m.message_date",nativeQuery = true)
	public List<Message> RetrieveConversation(@Param("id")Long conv_id,@Param("test")Boolean message_visibility);
}

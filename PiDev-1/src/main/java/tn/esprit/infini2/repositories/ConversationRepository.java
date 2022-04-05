package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Conversation;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Long> {
	@Query(value="SELECT * FROM Conversation c WHERE  c.conversation_visibility=:test",nativeQuery = true)
	public List<Conversation> RetrieveConversationsvisible(@Param("test")Boolean conversation_visibility);
}

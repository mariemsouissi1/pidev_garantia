package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Chat;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
	@Query(value="SELECT DISTINCT * FROM Chat C WHERE C.sender_id=:id1 OR"
	+ " C.sender_id=:id2 OR C.receiver_id=:id1 OR C.receiver_id=:id2",nativeQuery = true)
	public List<Chat> RetrieveConversations(@Param("id1")long sender_id, @Param("id2")long receiver_id);
	@Query(value="SELECT DISTINCT * FROM Chat C WHERE C.sender_id=:id1 OR C.sender_id=:id2 OR C.receiver_id=:id1 OR C.receiver_id=:id2 order by C.chat_date",nativeQuery = true)
	public List<Chat> RetrieveConversation(@Param("id1")long sender_id, @Param("id2")long receiver_id);

}

package se.ithuset.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import se.ithuset.pojos.DataStuff;

@Component
public interface DataRepository  extends JpaRepository<DataStuff, Integer> {
}

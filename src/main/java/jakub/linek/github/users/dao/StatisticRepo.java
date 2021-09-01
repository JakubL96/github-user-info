package jakub.linek.github.users.dao;


import jakub.linek.github.users.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StatisticRepo extends JpaRepository<Statistic, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO statistic (login, request_count) VALUES (:login, 1) " +
                    "ON CONFLICT (login) DO " +
                    "UPDATE SET request_count = statistic.request_count + 1 WHERE statistic.login=:login")
    void incrementCounter(String login);
}

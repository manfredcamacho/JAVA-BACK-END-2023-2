package ar.com.codoacodo.repository;

  //java collection framework: en java avanzado
  import java.util.List;

  import ar.com.codoacodo.entity.Speaker;

  public interface SpeakerRepository {
    void save(Speaker speaker);
    Speaker getById(Long id);
    void update(Speaker speaker);
    void delete(Long id);
    List<Speaker> findAll();
  }

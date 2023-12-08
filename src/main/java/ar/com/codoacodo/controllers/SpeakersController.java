package ar.com.codoacodo.controllers;

import ar.com.codoacodo.entity.ResponseData;
import ar.com.codoacodo.entity.Speaker;
import ar.com.codoacodo.repository.MySQLSpeakerRepository;
import ar.com.codoacodo.repository.SpeakerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/speakers")
public class SpeakersController extends HttpServlet {
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {

		SpeakerRepository repository = new MySQLSpeakerRepository();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		List<Speaker> speakers = repository.findAll();
		ResponseData<List<Speaker>> responseDataSpeaker = new ResponseData<>("ok", HttpServletResponse.SC_OK, speakers);
		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonResponse);
	}
}
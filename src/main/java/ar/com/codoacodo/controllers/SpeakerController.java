package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import ar.com.codoacodo.entity.ResponseData;
import ar.com.codoacodo.entity.Speaker;
import ar.com.codoacodo.entity.SpeakerRequest;
import ar.com.codoacodo.repository.MySQLSpeakerRepository;
import ar.com.codoacodo.repository.SpeakerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/speaker")
public class SpeakerController extends HttpServlet{

	private final ObjectMapper mapper = new ObjectMapper();
	private final SpeakerRepository repository = new MySQLSpeakerRepository();

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
	throws IOException {

		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		SpeakerRequest speakerRequest = mapper.readValue(request.getInputStream(), SpeakerRequest.class);
		Speaker speaker = new Speaker(
				speakerRequest.getName(),
				speakerRequest.getLastName(),
				speakerRequest.getEmail(),
				speakerRequest.getTopic(),
				LocalDate.now()
		);

		//TODO Validate form data

		repository.save(speaker);
		ResponseData<Speaker> responseDataSpeaker = new ResponseData<>("Speaker registered successfully", 201, speaker);
		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);

		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonResponse);
	}

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {

		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		SpeakerRepository repository = new MySQLSpeakerRepository();
		ResponseData<Speaker> responseDataSpeaker;

		String idParam = request.getParameter("id");
		if (idParam != null) {
			Long id = Long.parseLong(idParam);
			Speaker speaker = repository.getById(id);
			if(speaker == null)
				responseDataSpeaker = new ResponseData<Speaker>("Speaker with id "+ idParam+" not found.", HttpServletResponse.SC_NOT_FOUND, null);
			else
				responseDataSpeaker = new ResponseData<Speaker>("Speaker retrieved successfully.", HttpServletResponse.SC_OK, speaker);
		}else{
			responseDataSpeaker = new ResponseData<>("The 'id' parameter is required for this operation.", HttpServletResponse.SC_BAD_REQUEST, null);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);
		response.getWriter().print(jsonResponse);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}
}

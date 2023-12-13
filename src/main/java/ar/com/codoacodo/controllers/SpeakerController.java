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
			HttpServletResponse response
	) throws IOException {

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

        ResponseData<Speaker> responseDataSpeaker;
        try {
		    repository.save(speaker);
			response.setStatus(HttpServletResponse.SC_CREATED);
			responseDataSpeaker = new ResponseData<>("Speaker registered successfully", HttpServletResponse.SC_CREATED, speaker);

        }catch (IllegalArgumentException e){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseDataSpeaker = new ResponseData<>(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST, null);
        }
		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonResponse);
	}

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {

		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		ResponseData<Speaker> responseDataSpeaker;
		String idParam = request.getParameter("id");

		if (idParam != null) {
			Long id = Long.parseLong(idParam);
			Speaker speaker = repository.getById(id);
			if(speaker == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				responseDataSpeaker = new ResponseData<>("Speaker with id " + idParam + " not found.", HttpServletResponse.SC_NOT_FOUND, null);
			}else {
				response.setStatus(HttpServletResponse.SC_OK);
				responseDataSpeaker = new ResponseData<>("Speaker retrieved successfully.", HttpServletResponse.SC_OK, speaker);
			}
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseDataSpeaker = new ResponseData<>("The 'id' parameter is required for this operation.", HttpServletResponse.SC_BAD_REQUEST, null);
		}

		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonResponse);
	}

	@Override
	protected void doPut(
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		SpeakerRequest speakerRequest = mapper.readValue(request.getInputStream(), SpeakerRequest.class);
		ResponseData<Speaker> responseDataSpeaker;
		String idParam = request.getParameter("id");

		if (idParam != null) {
			Long id = Long.parseLong(idParam);
			Speaker speaker = repository.getById(id);
			if(speaker == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				responseDataSpeaker = new ResponseData<>("Speaker with id " + idParam + " not found.", HttpServletResponse.SC_NOT_FOUND, null);
			}else {
				speaker.setName(speakerRequest.getName());
				speaker.setLastName(speakerRequest.getLastName());
				speaker.setTopic(speakerRequest.getTopic());
				try {
					repository.update(speaker);
					response.setStatus(HttpServletResponse.SC_OK);
					responseDataSpeaker = new ResponseData<>("Speaker updated successfully.", HttpServletResponse.SC_OK, speaker);
				}catch (IllegalArgumentException e){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					responseDataSpeaker = new ResponseData<>(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST, null);
				}
			}
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseDataSpeaker = new ResponseData<>("The 'id' parameter is required for this operation.", HttpServletResponse.SC_BAD_REQUEST, null);
		}

		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonResponse);

	}

	@Override
	protected void doDelete(
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
		String id = request.getParameter("id");

		this.repository.delete(Long.parseLong(id));

		ResponseData<Speaker> responseDataSpeaker = new ResponseData<>("Speaker deleted successfully.",	HttpServletResponse.SC_OK,null);
		String jsonResponse = mapper.writeValueAsString(responseDataSpeaker);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(jsonResponse);
	}
}

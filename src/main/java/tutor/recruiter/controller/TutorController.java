package tutor.recruiter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutor.recruiter.exception.ResourceNotFoundException;
import tutor.recruiter.model.Tutor;
import tutor.recruiter.repository.TutorRepository;

@RestController
@RequestMapping("/tutor-recruiter")
public class TutorController {

	@Autowired
	private TutorRepository tutorRepository;

	@GetMapping("/tutors")
	public List<Tutor> getAllTutors() {
		return tutorRepository.findAll();
	}

	@GetMapping("/tutors/{id}")
	public ResponseEntity<Tutor> getTutorById(@PathVariable(value = "id") Long tutorId)
			throws ResourceNotFoundException {
		Tutor employee = tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor not found for this id :: " + tutorId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/tutors")
	public Tutor createTutor(@Valid @RequestBody Tutor tutor) {
		return tutorRepository.save(tutor);
	}

	@PutMapping("/tutors/{id}")
	public ResponseEntity<Tutor> updateTutor(@PathVariable(value = "id") Long tutorId,
											 @Valid @RequestBody Tutor tutorDetails) throws ResourceNotFoundException {
		Tutor tutor = tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor not found for this id :: " + tutorId));

		tutor.setName(tutorDetails.getName());
		tutor.setRate(tutorDetails.getRate());
		tutor.setZipcode(tutorDetails.getZipcode());
		tutor.setSubject(tutorDetails.getSubject());

		Tutor updatedTutor = tutorRepository.save(tutor);

		return ResponseEntity.ok(updatedTutor);
	}

	@DeleteMapping("/tutors/{id}")
	public Map<String, Boolean> deleteTutor(@PathVariable(value = "id") Long tutorId)
														throws ResourceNotFoundException {
		Tutor tutor = tutorRepository.findById(tutorId)
				.orElseThrow(() -> new ResourceNotFoundException("Tutor not found for this id :: " + tutorId));

		tutorRepository.delete(tutor);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}

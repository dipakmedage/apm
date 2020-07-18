package com.nclinic.apm.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nclinic.apm.models.Appointment;
import com.nclinic.apm.repositories.AppointmentRepository;

@Service
public class AppointmentService {

	private static String pName;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PaymentService paymentService;

	public String bookAppointment(Appointment appointment) {
		if (appointment.getAdvancePayment() > 0) {
			insertIntoPaymentifAdvanceTrue(appointment.getPatientName(), appointment.getAdvancePayment(),
					appointment.getTransactionId(), appointment.getAppointmentDate());
		}
		paymentService.findandUpdateAdvancePay(appointment.getPatientName());
		appointmentRepository.save(appointment);
		return "ok";
	}

	public Appointment setAppointmentFromExcel(XSSFRow row, DataFormatter formatter) throws ParseException {
		String patientname;
		Date appointmentDate;
		Appointment appointment = new Appointment();
		patientname = formatter.formatCellValue(row.getCell(1));
		if (!patientname.equals("")) {
			patientname = (String) row.getCell(1).getStringCellValue();
			if (pName == "" || patientname != pName)
				pName = patientname;
		} else {
			patientname = pName;
		}

		appointment.setPatientName(patientname);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		appointmentDate = sdf.parse(formatter.formatCellValue(row.getCell(6)));

		// appointmentDate = row.getCell(6).getDateCellValue();
		appointment.setAppointmentDate(appointmentDate);

		String timeSlot = formatter.formatCellValue(row.getCell(7));
		appointment.setTimeSlot(timeSlot);

		appointment.setAdvancePayment((int) row.getCell(11).getNumericCellValue());

		appointment.setSessionApp((String) row.getCell(12).getStringCellValue());

		appointment.setTransactionId((String) row.getCell(14).getStringCellValue());

		appointment.setIsActive('Y');

		appointment.setCreatedBy("Neha");

		return appointment;
	}

	public void saveFromExcel(List<Appointment> appointments) {
		appointmentRepository.saveAll(appointments);
	}

	public List<Appointment> getAllAppointments() {
		return (List<Appointment>) appointmentRepository.findAll();
	}

	private boolean insertIntoPaymentifAdvanceTrue(String patientName, int advancePay, String transactionId,
			Date appointmentDate) {

		paymentService.fillInAdvance(patientName, advancePay, transactionId, appointmentDate);
		return true;
	}
}

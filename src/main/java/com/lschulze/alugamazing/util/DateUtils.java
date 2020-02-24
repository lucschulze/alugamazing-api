package com.lschulze.alugamazing.util;

import java.time.LocalDate;
import java.time.Period;

import com.lschulze.alugamazing.model.Status;

public class DateUtils {

	public static Status reservedType(LocalDate firstDay) {
		LocalDate today = LocalDate.now();
		Period period = Period.between(firstDay, today);
		int diff = period.getDays();
		return (diff != 0) ? Status.RESERVED : Status.RENTED;
	}
	
	public static Status checkStatus(LocalDate fisrtDay ,LocalDate lastDay) {
		LocalDate today = LocalDate.now();
		
		Period periodInitial = Period.between(fisrtDay, today);
		int pInital = periodInitial.getDays();
		
		Period periodFinal = Period.between(lastDay, today);
		int pFinal = periodFinal.getDays();
		
		if (pInital < 0 && pFinal < 0) {
			return Status.RESERVED;
		} else if (pInital >= 0 && pFinal <= 0) {
			return Status.RENTED;
		} else {
			return Status.PENDENT;
		}
	}
}

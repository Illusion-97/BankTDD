package fr.dawan.banktdd.tools;

import org.modelmapper.ModelMapper;

public class DtoTools {

	private static final ModelMapper myMapper = new ModelMapper();


	public static <TSource,TDestination> TDestination convert( TSource obj, Class<TDestination> clazz) {

		return obj == null ? null : myMapper.map(obj, clazz);
	}
}

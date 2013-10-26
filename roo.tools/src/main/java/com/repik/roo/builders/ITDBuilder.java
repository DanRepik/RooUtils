package com.repik.roo.builders;

import org.springframework.roo.classpath.details.ItdTypeDetailsBuilder;
import org.springframework.roo.classpath.details.MethodMetadata;
import org.springframework.roo.classpath.details.MethodMetadataBuilder;
import org.springframework.roo.model.JavaType;

/**
 * This class provides a facade to build ITD's
 * 
 * @author Dan Repik
 *
 */
public class ITDBuilder extends IndentifableAssetBuilder<ItdTypeDetailsBuilder> {

	private ItdTypeDetailsBuilder builder ;
	
	public ITDBuilder( ItdTypeDetailsBuilder builder ) {
		super( builder ) ;
	}
	
	public ITDBuilder import_( String typeName ) {
		builder.getImportRegistrationResolver().addImport( new JavaType( typeName )) ;
		return this ;
	}
	
	public ITDBuilder annotation( AnnotationBuilder builder ) {
		addAnnotation(builder) ;
		return this ;
	}
	
	public ITDBuilder method( MethodBuilder methodBuilder ) {
		if ( methodBuilder == null ) {
			return this ;
		}
		
		MethodMetadata metadata = methodBuilder.build() ;
		for ( MethodMetadataBuilder method : builder.getDeclaredMethods()) {
			if ( method.getMethodName().equals( metadata.getMethodName())) {
				return this ;
			}
		}
		
		builder.addMethod(metadata);
		return this ;
	}
}

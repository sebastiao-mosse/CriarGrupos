package com.produsol.cadastro.grupo.action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.DocumentoRepository;
import com.produsol.cadastro.grupo.services.DocumentService;
import com.produsol.cadastro.grupo.vo.Documento;


public class CadastroDocumentoActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	DocumentoRepository documentoRepository = new DocumentoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		/*
		Documento documento = new Documento();
		List<Documento> lista = new ArrayList<Documento>();
		documentoRepository.save(lista);
		*/
	}
	
	

	
}
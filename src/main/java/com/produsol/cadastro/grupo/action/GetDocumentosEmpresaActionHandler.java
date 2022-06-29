package com.produsol.cadastro.grupo.action;
import java.util.Arrays;
import java.util.List;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.DocumentService;

public class GetDocumentosEmpresaActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	Propriedades api = new  Propriedades();
	DocumentService documento = new DocumentService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		Input caminho = (Input)executionContext.getContextInstance().getVariable("caminho");
		
		String resultado = documento.getDocumentUUID(caminho.getValue().toString(), api.GetEnviromentVariables().get(9).replace("\"", ""))
				.toString().replace("[", "").replace("]", "");
				
		List<String> lista = Arrays.asList(resultado.split(",", -1));
		
		Input idDar = new Input();
		idDar.setName("idDar");
		idDar.setLabel("idDar");
		idDar.setValue(String.valueOf(lista.get(0).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idDar", idDar);
		
		Input idAlvara = new Input();
		idAlvara.setName("idAlvara");
		idAlvara.setLabel("idAlvara");
		idAlvara.setValue(String.valueOf(lista.get(1).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idAlvara", idAlvara);
		
		Input idNif = new Input();
		idNif.setName("idNif");
		idNif.setLabel("idNif");
		idNif.setValue(String.valueOf(lista.get(2).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idNif", idNif);
		
		Input idDiario = new Input();
		idDiario.setName("idDiario");
		idDiario.setLabel("idDiario");
		idDiario.setValue(String.valueOf(lista.get(3).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idDiario", idDiario);
		
		Input idFichaEmpresa = new Input();
		idFichaEmpresa.setName("idFichaEmpresa");
		idFichaEmpresa.setLabel("idFichaEmpresa");
		idFichaEmpresa.setValue(String.valueOf(lista.get(4).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idFichaEmpresa", idFichaEmpresa);
			
		Input idRegComercial = new Input();
		idRegComercial.setName("idRegComercial");
		idRegComercial.setLabel("idRegComercial");
		idRegComercial.setValue(String.valueOf(lista.get(5).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idRegComercial", idRegComercial);
			
		Input idRegEstatistico = new Input();
		idRegEstatistico.setName("idRegEstatistico");
		idRegEstatistico.setLabel("idRegEstatistico");
		idRegEstatistico.setValue(String.valueOf(lista.get(6).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idRegEstatistico", idRegEstatistico);

	}
	
	public String getSelectedValue(Select selectedValue)
	{
		String retorno ="";
		for(int i=0; i<selectedValue.getOptions().size();i++)
	     {
	    	 if(selectedValue.getOptions().get(i).isSelected())
	    	 {
	    	     retorno = selectedValue.getOptions().get(i).getLabel();
	    	 }
	     }
		return retorno;
	}
}
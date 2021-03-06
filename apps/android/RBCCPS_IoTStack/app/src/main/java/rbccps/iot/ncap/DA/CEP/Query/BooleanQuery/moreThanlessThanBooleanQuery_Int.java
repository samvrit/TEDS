package rbccps.iot.ncap.DA.CEP.Query.BooleanQuery;

import java.util.ArrayList;
import java.util.HashMap;

public class moreThanlessThanBooleanQuery_Int extends BooleanQuery{
	int parameter;
	public moreThanlessThanBooleanQuery_Int(String in_query,int queryParameters)
	{
		super(in_query);
		this.parameter=queryParameters;
		 
	}
	public boolean process(HashMap<String,String> currentDataMap)
	{
		query=query.trim();
		//System.out.println(currentDataMap.toString());  
		String[] parametersNames=null;
		if(query.contains(">"))
			parametersNames=query.split(">");
		else
			parametersNames=query.split("<");
		parametersNames[0]=parametersNames[0].replaceAll("\\s+","");
		parametersNames[1]=parametersNames[1].replaceAll("\\s+","");
		ArrayList<Integer> parametersStrings=new ArrayList<Integer>();
		if(parameter==0)
		{		
			parametersStrings.add(Integer.parseInt(currentDataMap.get(parametersNames[0])));
			parametersStrings.add(Integer.parseInt(currentDataMap.get(parametersNames[1])));
		}
		else
		{	
			String a=parametersNames[0];
			parametersStrings.add(Integer.parseInt(currentDataMap.get(a)));
			String userGivenValue=parametersNames[1].replace("(","");
			userGivenValue=userGivenValue.replace(")","");
			parametersStrings.add(Integer.parseInt(userGivenValue));
		}
		
		//checking for equality
		if(query.contains(">"))
			return (parametersStrings.get(0).intValue() > parametersStrings.get(1).intValue());
		else
			return (parametersStrings.get(0).intValue() < parametersStrings.get(1).intValue());
	}

}

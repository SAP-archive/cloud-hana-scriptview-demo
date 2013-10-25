CREATE PROCEDURE "NEO_<YOUR_SCHEMA_ID>"."manager/SCRIPTVIEW/proc" ( OUT var_out "NEO_<YOUR_SCHEMA_ID>"."manager/SCRIPTVIEW/proc/tabletype/VAR_OUT" ) language sqlscript sql security definer reads sql data AS   
BEGIN  
result = SELECT * FROM "NEO_<YOUR_SCHEMA_ID>"."MANAGERS";  
var_out = SELECT  
       A."ID",  
       A."NAME",  
       A."REGION",  
       A."BUDGET",  
       A."BUDGET"*100/(select sum("BUDGET") from "NEO_<YOUR_SCHEMA_ID>"."MANAGERS") as "BUDGET_PERCENTAGE_SHARE"  
FROM :result A;   
END;  

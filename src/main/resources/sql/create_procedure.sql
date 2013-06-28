CREATE PROCEDURE "manager/SCRIPTVIEW/proc" ( OUT var_out "manager/SCRIPTVIEW/proc/tabletype/VAR_OUT" ) language sqlscript sql security definer reads sql data AS   
BEGIN  
result = SELECT * FROM "MANAGERS";  
var_out = SELECT  
       A."ID",  
       A."NAME",  
       A."REGION",  
       A."BUDGET",  
       A."BUDGET"*100/(select sum("BUDGET") from "MANAGERS") as "BUDGET_PERCENTAGE_SHARE"  
FROM :result A;   
END;  

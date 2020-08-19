#encoding "UTF-8"
#GRAMMAR_ROOT date_res
/* гармматика дл¤ дат без пометок */
Descr  -> "год" | "г";


//день недели
day -> Word<kwtype=week> interp(Date.Day);

//мес¤ц	
Mounth -> Word<kwtype = dates> interp(Date.Mounth);	

//число
Num -> AnyWord<wff=/[1-3][0-9]/> ;
Num -> AnyWord<wff=/[1-9]/> ;
Num -> "число"  AnyWord<wff=/[1-3]?[0-9]?/ > interp(Date.Num);  
Num -> Word<GU = ['NUM']|['ANUM']>+ interp(Date.Num) Word<kwset = [dates]> ;
Num -> "число" Word<GU = ['NUM']|['ANUM']>+ interp(Date.Num);  


//год
Year -> AnyWord<wff =/[1-9]?[0-9]?[0-9]?[0-9]?/>interp(Date.Year) Descr;
Year -> Word<GU = ['ANUM']|['NUM']>+interp(Date.Year) Descr;


date_1 -> AnyWord<wff =/[0-3]?[0-9]?\.[0-2]?[0-9]?\.[0-9]?[0-9]?[0-9]?[0-9]?/> interp(Date.Year);
date_2 -> (Num interp(Date.Num))Mounth(Year)(Descr); 
date_2 -> (Mounth)Year;
date_2 -> day;

date ->  date_1 | date_2; 
date -> Word<kwtype=add> interp(Date.SpecialWords) (Word<GU=['ANUM']|['NUM']> interp(Date.Num))Word<kwset=[exact2, word]> interp(Date.Name);
/* гармматика дл¤ дат с пометками */

date_op  -> AnyWord<kwtype = open> date;
date_cl  -> AnyWord<kwtype = close> date;
date_str -> AnyWord<kwtype = str> date interp(Date.TypeBegin="true") SimConjAnd date interp(Date.TypeEnd="true");

date_res -> date_op interp(Date.TypeBegin="true") date_cl interp(Date.TypeEnd="true");
date_res -> date_op interp(Date.TypeBegin="true");
date_res -> date_cl interp(Date.TypeEnd="true");
date_res -> date_str;
date_res -> AnyWord<kwtype = exact> interp(Date.TypeRes="true");
date_res -> date interp(Date.TypeRes="true");
























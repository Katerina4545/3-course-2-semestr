#encoding "UTF-8"
#GRAMMAR_ROOT date_res
/* гармматика дл¤ дат без пометок */
Descr  -> "год" | "г";

//число
Num_1 -> AnyWord<wff=/[1-3]?[0-9]?/ > Mounth |  AnyWord<wff=/[1-3]?[0-9]?/ > "число" | "число"  AnyWord<wff=/[1-3]?[0-9]?/ >;  
Num_2 -> Word<GU = ['NUM']|['ANUM']>+ Mounth | Word<GU = ['NUM']|['ANUM']>+  "число" | "число" Word<GU = ['NUM']|['ANUM']>+;  
Num   -> Num_1|Num_2;
//день недели
day -> Word<kwtype=week> interp(Date.Day);
//мес¤ц	
Mounth -> Word<kwtype = dates> interp(Date.Mounth);	
//год
Year -> AnyWord<wff =/[1-9]?[0-9]?[0-9]?[0-9]?/>interp(Date.Year) Descr;
Year -> Word<GU = ['ANUM']|['NUM']>+interp(Date.Year) Descr;


date_1 -> AnyWord<wff =/[1-3]?[0-9]?\.[1-2]?[0-9]?\.[1-9]?[0-9]?[0-9]?[0-9]?/>;
date_2 -> (Num)Mounth(Year)(Descr); 
date_2 -> (Mounth)Year;
date_2 -> Num_2;
date_2 -> day;

date ->  date_1<kwset=~["время"]> | date_2<kwset=~["время"]>; 
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
























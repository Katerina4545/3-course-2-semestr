#encoding "UTF-8"

/* Точное время */

time_0  -> AnyWord<wff=/[0-2][0-9]:[0-5][0-9]/> interp(Time.Hour);
time_0  -> Word<GU = ['NUM']|['ANUM'], kwset=~["дата"]>+ interp(Time.Minutes) Word<kwset = [minute]>;
time_0  -> AnyWord<GU = ['NUM']|['ANUM'], kwset=~["дата"]>+ interp(Time.Hour) Word<kwset = [hour]>;
time_0  -> Word<kwtype="time_", kwset=~["дата"]>+ interp(Time.Hour) (Word<GU = ['NUM']|['ANUM'], kwset=~["дата"]>+ interp(Time.Minutes))("ноль"+ interp(Time.Minutes)) ; 
time_0  -> AnyWord<wff=/[0-2][0-9]/, kwset=~["дата"]> interp(Time.Hour) |  AnyWord<wff=/[0-2][0-9]/>interp(Time.Hour) Word<kwtype=hour> | AnyWord<wff=/[0-2][0-9]/>interp(Time.Minutes) Word<kwtype=minute>;																		
time_0  -> Word<GU=~['NUM']|~['ANUM'], wff=/[^0-2][^0-9]/> Word<kwtype = [minute]> interp(Time.Minutes);
time_0  -> Word<GU=~['NUM']|~['ANUM'], wff=/[^0-2][^0-9]/> Word<kwtype = [hour]> interp(Time.Hour);
time_00 -> time_0 (SimConjAnd) time_0;	//двадвать минут и один час
time_0  -> time_00; 
time_0  -> Word<kwtype=exact2> interp(Time.TimeOfDay);

/* до половины : 
"десять минут первого", 
"двацать пять минут пятого" */

time_1 -> AnyWord<gram = 'NUM, nom'>+ interp(Time.Minutes) "минута" Word<kwtype=time_Day2>+ interp(Time.Hour);

// половина 
time_2 -> Word<kwtype=half>interp(Time.Minutes="30") Word<kwtype=time_Day1> interp(Time.Hour);
time_2 -> ("день")("вечер") Word<kwset=[time_Day1], wff=/[п][о][л].*/>  interp(Time.Hour; Time.Minutes="30")  ("день")("вечер"); //"полчетвертого"
time_2 -> "утро"  Word<kwset=[time_Night1, hour]>interp(Time.Hour; Time.Minutes="30");
time_2 ->   Word<kwset=[time_Night1, hour]>interp(Time.Hour; Time.Minutes="30") "утро";
time_2 -> "ночь"  Word<kwset=[time_Night1, hour]>interp(Time.Hour; Time.Minutes="30");
time_2 ->   Word<kwset=[time_Night1, hour]>interp(Time.Hour; Time.Minutes="30") "ночь";

// после половины 
time_3 -> ("день")("вечер")Word<kwtype=after_half>  Word<kwtype=part> interp(Time.Minutes) Word<kwset=[time_Day2, hour]> interp(Time.Hour) ("день")("вечер");
time_3 -> "утро" Word<kwtype=after_half>  Word<kwtype=part> interp(Time.Minutes) Word<kwset=[time_Night2, hour]> interp(Time.Hour);
time_3 -> Word<kwtype=after_half>  Word<kwtype=part> interp(Time.Minutes) Word<kwset=[time_Night2, hour]> interp(Time.Hour) "утро";
time_3 -> "ночь" Word<kwtype=after_half>  Word<kwtype=part> interp(Time.Minutes) Word<kwset=[time_Night2, hour]> interp(Time.Hour);
time_3 -> Word<kwtype=after_half>  Word<kwtype=part> interp(Time.Minutes) Word<kwset=[time_Night2, hour]> interp(Time.Hour) "ночь";

//относительное время
time_4 -> Word<kwtype=add>interp(Time.SpecialWords) (Word<GU=[ANUM]|[NUM]> interp(Time.Num)) Word<kwset=[hour,minute]> interp(Time.Name);

time -> time_0  | time_1 interp(Time.Type="before_half") | time_2 interp(Time.Type="half")| time_3 interp(Time.Type="after_half")|time_4;

time_op  -> Word<kwtype = open> time;
time_cl  -> Word<kwtype = close> time;
time_str -> Word<kwtype = str> time interp(Time.TypeBegin="true") SimConjAnd time interp(Time.TypeEnd="true");
time_add -> Word<kwtype=add>interp(Time.SpecialWords) (Word<GU=['ANUM']|['NUM']> interp(Time.Num))Word<kwset=[hour, minute]> interp(Time.Name);

time_res -> time_op interp(Time.TypeBegin="true") time_cl interp(Time.TypeEnd="true");
time_res -> time_op interp(Time.TypeBegin="true");
time_res -> time_cl interp(Time.TypeEnd="true");
time_res -> time_add;
time_res -> time_str;
time_res -> time interp(Time.TypeRes="true");
























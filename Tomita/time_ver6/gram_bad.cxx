#encoding "UTF-8"
bad -> trash interp(Time.badWords ="мусор") ;

trash -> 		Word<kwtype=none>		 small_trash 		(small_trash) (small_trash)		 EOSent		 |		"а"  EOSent;         
        
			small_trash -> Word<wff=/[а-я]{1,2}|[А-Я]{1,2}/> ;

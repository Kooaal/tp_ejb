package ema.tpjee.utils;

import java.util.Iterator;
import java.util.List;

public class Paging {
	
	public static <T> T prior(List<T> list, T ref) {
		if (ref == null)
			return null;
		T result = null;
		T prior = null, current = null;
		for (Iterator<T> i = list.iterator(); i.hasNext();) {
			prior = current;
			current = i.next();
			if (current == ref) {
				result = prior;
				break;
			}
		}
		if (result == null)
			result = ref;
		return result;
	}	
	
	public static <T> T next(List<T> list,T ref) {
		if (ref == null)
			return null;
		T result = null;
		for (Iterator<T> i = list.iterator(); i.hasNext();) {
			T current = i.next();
			if (current == ref) {
				if (i.hasNext())
					result = i.next();
				break;
			}
		}
		if (result == null)
			result = ref;
		return result;
	}

}

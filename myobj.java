import java.util.*;

public  class myobj
{
int myinteger;
int stage;
double mydouble;
String datatype;
 public static HashMap<String,myobj> sym=new HashMap<String,myobj>();
	
    public double getvalue(int lvl, String varname)
    {
        if(stage==lvl)
        {
            //if value is in same block
            if(!datatype.equals("real"))			//if not real return integer
			{
				return (double) myinteger;
                
            }
            else
            {
                return mydouble;					// else return double
            }
        }
        else
        {
            //If value is not in same block

            if(sym.containsKey(varname)){
                myobj myobj1 = sym.get(varname);
                if(lvl >= myobj1.stage)
                {
                        if(!myobj1.datatype.equals("real"))	//if not real return integer 
						{
                        return (double) myobj1.myinteger;                            
                        }
                        else
                        {
						return myobj1.mydouble;				//else return double 
                        }
                }
            }
            return 0;
        }
    }

		public void addValue(int level,double val)		// Level and value of object
	{
			if (datatype=="int") {
			
			 mydouble = val;
				datatype="real";
				
				myinteger = (int)val;			// convert to int
				stage = level;
				datatype = "int";
			}
			else
			{
				mydouble = val;
				datatype="real";
			}
	}
	
	 public void addValue( int level, double val, String type) 		//level value and type
    {
        if (type.equals("int")) {
            //integer
            myinteger = (int)val;
            stage = level;
            datatype = "int";
        }
        else
        {
            mydouble = val;
            stage = level;
            datatype="real";
        }
    }

	
    public boolean sametype(double a)
    {
        long iPart = (long) a;
        double fPart = a - iPart;

        if(Double.compare(fPart,0.0)==0)
        {

            if(datatype.equals("int"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(datatype.equals("real"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

}

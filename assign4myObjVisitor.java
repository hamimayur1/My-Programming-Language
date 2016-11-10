// Generated from assign4.g4 by ANTLR 4.5

  import java.util.*;
import java.lang.Exception;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link assign4Visitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class assign4myObjVisitor extends assign4BaseVisitor<Double> {
	
	int stage=0;
	public static final double smallval = 0.0000000001;

	 
	 
	 @Override public Double visitAssignRealSimpleExpresssion(assign4Parser.AssignRealSimpleExpresssionContext ctx)  {
		 String id = ctx.IDENT().getText();
		 double simpleexpressionans  = visit(ctx.simpleExpression());
		 
        if(myobj.sym.get(id).datatype.equals("real"))
        {
			myobj obj = new myobj();
			obj.addValue(stage,simpleexpressionans);
			myobj.sym.put(id,obj);
        }
        else
        {
            System.out.println("Datatype typecast mismatch");
        }
		return 0.0;
}
	 
	 
	 @Override public Double visitAssignIntSimpleExpression(assign4Parser.AssignIntSimpleExpressionContext ctx)  {
		
		 String id = ctx.IDENT().getText();
		 Double simpleexpressionans  = visit(ctx.simpleExpression());
		
        if(myobj.sym.get(id).datatype.equals("int"))
        {
            if(myobj.sym.get(id).sametype(simpleexpressionans))
            {
			
			myobj obj = new myobj();
			obj.addValue(stage,simpleexpressionans);
         
                myobj.sym.put(id,obj);
            }
            else
            {
                int temp = simpleexpressionans.intValue();
				myobj obj = new myobj();
				obj.addValue(stage,temp);         
                myobj.sym.put(id,obj);
            }
        }
        else
        {
            System.out.println("Error: Datatype doesn't Match");
        }
		
return 0.0;
}
	 
	 
	 @Override public Double visitAssignSimpleExpression(assign4Parser.AssignSimpleExpressionContext ctx)  {
		
		 String id = ctx.IDENT().getText();
		 double simpleexpressionans  = visit(ctx.simpleExpression()); //goto simple expression and find answer of expression
		
        {
			if(myobj.sym.get(id).sametype(simpleexpressionans))
			{
			myobj obj = new myobj();
					obj.addValue(stage, simpleexpressionans,myobj.sym.get(id).datatype);
				myobj.sym.put(id,obj);
			}
			else
			{
				System.out.println("Error: Datatype doesn't Match");
			}
}
return 0.0;
}
	 
	  @Override public Double visitExpressionStringSimple(assign4Parser.ExpressionStringSimpleContext ctx)  {
		
		 Double val= visit(ctx.simpleExpression());
		 System.out.print(val);
	   return 0.0;
	 }
	 
	 
	  @Override public Double visitPrintExpressionStringSimple(assign4Parser.PrintExpressionStringSimpleContext ctx) {
		visitChildren(ctx);
	   return 0.0;
	   
	   }
	 
	 
	   @Override public Double visitExpressionStringText(assign4Parser.ExpressionStringTextContext ctx)  {
		
		 String text = ctx.TEXT().getText();
		 System.out.print(text);
	   return 0.0;
	 }
	 
	 @Override public Double visitPrintFactor(assign4Parser.PrintFactorContext ctx) {
		Double val= visit(ctx.factor());
	   System.out.print(val);
	   return 0.0;
	   
	   }
	
	
	@Override public Double visitInputVar(assign4Parser.InputVarContext ctx) { 	//action class for InputVariable
	
	String id = ctx.IDENT().getText();
	
	Scanner inp = new Scanner(System.in);
	System.out.println("Enter the value of " + id);
	double value = inp.nextDouble();
	
	myobj obj = new myobj();
			obj.addValue(stage,value);
	myobj.sym.put(id, obj);
	//System.out.println(sym.get($IDENT.text));

	return 0.0; 
	
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	
	@Override public Double visitIntident(assign4Parser.IntidentContext ctx) { 

	String id = ctx.IDENT().getText();
	
 if(myobj.sym.containsKey(id))
    {
        myobj myobj1 = myobj.sym.get(id);
        if(myobj1.stage==stage)								//compares if object is at same stage as that of stage variable
        {
            System.out.println("Error : Duplicate defination of Variable");
        }
        else
        {
			myobj obj = new myobj();
			obj.addValue(stage,0,"int");
            myobj.sym.put(id,obj);
        }
    }
	else
    {
		myobj obj = new myobj();
			obj.addValue(stage,0,"int");
        myobj.sym.put(id,obj);
    }

//return visitChildren(ctx); 
return 0.0;
}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Double visitRealident(assign4Parser.RealidentContext ctx) {
		
			String id = ctx.IDENT().getText();


if(myobj.sym.containsKey(id))
    {
        myobj myobj1 = myobj.sym.get(id);
        if(myobj1.stage==stage)
        {
			System.out.println("Error : Variable already Defined");
			
        }
        else
        {
            myobj obj = new myobj();
			obj.addValue(stage,0,"real");
            myobj.sym.put(id,obj);
			
        }
    }
    else
    {
		myobj obj = new myobj();
			obj.addValue(stage,0.0,"real");
        myobj.sym.put(id,obj);
    }
 //return visitChildren(ctx); 


return 0.0;
}


 @Override public Double visitSimpleExpressionAdd(assign4Parser.SimpleExpressionAddContext ctx)  {
		
		 double first = visit(ctx.first);		// get value of first 
		 double next = visit(ctx.next);			// get value of next	
		 
	   return (first+next);						//return sum 
	 }
	 

 @Override public Double visitSimpleExpressionSub(assign4Parser.SimpleExpressionSubContext ctx)  {
		
		 double first = visit(ctx.first);		// get value of first 
		 double next = visit(ctx.next);			// get value of next	
		 
	   return (first-next);						//return sum 
	 }
	 
 @Override public Double visitSimpleExpressionTerm(assign4Parser.SimpleExpressionTermContext ctx)  {
		
		 double val = visit(ctx.term());	//process value of term	
		 return val;						//return sum 
	 }	 
	 
	 
	 
	 @Override public Double visitTermMul(assign4Parser.TermMulContext ctx)  {
		
		 double first = visit(ctx.first);		// get value of first 
		 double next = visit(ctx.next);			// get value of next	
		 
	   return (first*next);						//return 
	 }
	 
	 
	 
	 @Override public Double visitTermDiv(assign4Parser.TermDivContext ctx)  {
		
		 double first = visit(ctx.first);		// get value of first 
		 double next = visit(ctx.next);			// get value of next	
		 
	   return (first/next);						//return 
	 }
	 
	 @Override public Double visitTermFactor(assign4Parser.TermFactorContext ctx)  {
		
		 double val = visit(ctx.factor());	//process value of term	
		 return val;						//return 
	 }
	 
	 
	@Override public Double visitFactorIdent(assign4Parser.FactorIdentContext ctx)  {
		
		  String id = ctx.IDENT().getText();
		 double val = myobj.sym.get(id).getvalue(stage,id);
		 return val;						//return 
		
	 }
	 
	  
	@Override public Double visitFactorInt(assign4Parser.FactorIntContext ctx)  {
		
		String inte = ctx.INT().getText();
		double val =Double.parseDouble(inte);
		return val;
	 }
	 
	 @Override public Double visitFactorReal(assign4Parser.FactorRealContext ctx)  {
		
		String real = ctx.REAL().getText();
		double val =Double.parseDouble(real);
		return val;
	 }
		
	
	 @Override public Double visitBeginBlock(assign4Parser.BeginBlockContext ctx)  {
		
		stage = stage + 1;
		return 0.0;
	 }
	 
	 
	 @Override public Double visitEndBlock(assign4Parser.EndBlockContext ctx)  {
		
		stage = stage -1;
		return 0.0;
	 }
	 
	 
	 
	
@Override
    public Double visitIfstatement(assign4Parser.IfstatementContext ctx) {

	//String id = ctx.IF().getText();
	//System.out.println(id);
        List<assign4Parser.ConditionblockContext> conditions =  ctx.conditionblock();

        boolean evalBlock = false;

        for(assign4Parser.ConditionblockContext condition : conditions) {

            Double eval = this.visit(condition.simpleExpression());

            if(eval != 1.0) {

			}
			else if (eval == 1){
	              // eval this block when eval ==1 
                this.visit(condition.stat_block());
                break;			
				
			}
        }

        if(!evalBlock && ctx.stat_block() != null) {
			
            this.visit(ctx.stat_block());
        }

        return 0.0;
    }
	
	
	 @Override public Double visitDescriptorFunc(assign4Parser.DescriptorFuncContext ctx)  {
	
	 
		 
		 String id = ctx.IDENT().getText();
		 
    System.out.println("Var Name: " + id);
    System.out.println("Var Type: " + myobj.sym.get(id).datatype);
    if(myobj.sym.get(id).datatype.equals("int"))
    {
        System.out.println("Var Value is: " + myobj.sym.get(id).myinteger);
    }
    else if(myobj.sym.get(id).datatype.equals("real"))
    {
        System.out.println("Var Value is : " + myobj.sym.get(id).mydouble);
    }
	
	return 0.0;
}

	
	@Override
    public Double visitRelationalExpr(assign4Parser.RelationalExprContext ctx) {

        Double left = this.visit(ctx.simpleExpression(0));
        Double right = this.visit(ctx.simpleExpression(1));

        switch (ctx.op.getType()) {
            case assign4Parser.LT:
                if(left < right){
					return 1.0;
				}
				else{
					return 0.0;
				}
            case assign4Parser.GT:
                if(left < right){
					return 0.0;
				}
				else{
					return 1.0;
				}
            case assign4Parser.LTEQ:
                if(left <= right){
					return 1.0;
				}
				else{
					return 0.0;
				}
            
			case assign4Parser.GTEQ:
                if(left < right){
					return 0.0;
				}
				else{
					return 1.0;
				}
            default:
                throw new RuntimeException("operator not found: " + assign4Parser.tokenNames[ctx.op.getType()]);
        }
    }
	
	@Override
    public Double visitEqualityExpr(assign4Parser.EqualityExprContext ctx) {

        Double left = (Double)this.visit(ctx.simpleExpression(0));
        Double right = (Double)this.visit(ctx.simpleExpression(1));

        switch (ctx.op.getType()) {
            case assign4Parser.EQ:
				if(left instanceof Double && right instanceof Double){
					if( smallval >  Math.abs(left - right) ){
						return 0.0;
					}
					else{
						return 1.0;
					
					}
				}
				else{
					if(!right.equals(left)){
						return 0.0;
					}
					else{
						return 1.0;
					}
				}
            case assign4Parser.NEQ:
				if(left instanceof Double && right instanceof Double){
					if(Math.abs(left - right) >= smallval){
						return 0.0;
					}
					else{
						return 0.0;
					}
				}
				else{
					if(!left.equals(right)){
						return 1.0;
					}
					else{
						return 0.0;
					}
				}
            default:
                throw new RuntimeException("unknown operator: " + assign4Parser.tokenNames[ctx.op.getType()]);
        }
    }
	
	 @Override
    public Double visitForstatement(assign4Parser.ForstatementContext ctx) {

		Double value = this.visit(ctx.assignmentDeclaration(0));
		Double value1 = this.visit(ctx.simpleExpression());

        while(value1 == 1.0) {

            this.visit(ctx.stat_block());
			Double value2 = this.visit(ctx.assignmentDeclaration(1));
            value1 = this.visit(ctx.simpleExpression());
        }

        return 0.0;
    }
	
	@Override
    public Double visitWhilestatement(assign4Parser.WhilestatementContext ctx) {

        Double value = this.visit(ctx.simpleExpression());

        while(value == 1.0) {

            this.visit(ctx.stat_block());

            value = this.visit(ctx.simpleExpression());
        }

        return 0.0;
    }

  public static void main(String[] args) throws Exception {
      if (args.length == 0) {
            args = new String[]{"input2.txt"};
        }

        System.out.println("parsing: " + args[0]);

        assign4Lexer lexer = new assign4Lexer(new ANTLRFileStream(args[0]));		//create instance of lexer and parser
        assign4Parser parser = new assign4Parser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();				//start rule program();	and create a tree from speciffied node
        assign4myObjVisitor visitor = new assign4myObjVisitor(); //start execution from the execution
		visitor.visit(tree);
      }
	
}

	
	
	

    
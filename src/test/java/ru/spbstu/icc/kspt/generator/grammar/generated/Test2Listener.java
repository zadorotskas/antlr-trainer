// Generated from C:/Users/zador/IdeaProjects/antlr-trainer/src/test/resources/grammar/parser\Test2.g4 by ANTLR 4.10.1
package ru.spbstu.icc.kspt.generator.grammar.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Test2Parser}.
 */
public interface Test2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Test2Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(Test2Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(Test2Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(Test2Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(Test2Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstDeclaration(Test2Parser.ConstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstDeclaration(Test2Parser.ConstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#constAssign}.
	 * @param ctx the parse tree
	 */
	void enterConstAssign(Test2Parser.ConstAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#constAssign}.
	 * @param ctx the parse tree
	 */
	void exitConstAssign(Test2Parser.ConstAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(Test2Parser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(Test2Parser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(Test2Parser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(Test2Parser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(Test2Parser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(Test2Parser.ProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(Test2Parser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(Test2Parser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operatorAssign}.
	 * @param ctx the parse tree
	 */
	void enterOperatorAssign(Test2Parser.OperatorAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operatorAssign}.
	 * @param ctx the parse tree
	 */
	void exitOperatorAssign(Test2Parser.OperatorAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operatorCall}.
	 * @param ctx the parse tree
	 */
	void enterOperatorCall(Test2Parser.OperatorCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operatorCall}.
	 * @param ctx the parse tree
	 */
	void exitOperatorCall(Test2Parser.OperatorCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operatorBegin}.
	 * @param ctx the parse tree
	 */
	void enterOperatorBegin(Test2Parser.OperatorBeginContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operatorBegin}.
	 * @param ctx the parse tree
	 */
	void exitOperatorBegin(Test2Parser.OperatorBeginContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operatorIf}.
	 * @param ctx the parse tree
	 */
	void enterOperatorIf(Test2Parser.OperatorIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operatorIf}.
	 * @param ctx the parse tree
	 */
	void exitOperatorIf(Test2Parser.OperatorIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#operatorWhile}.
	 * @param ctx the parse tree
	 */
	void enterOperatorWhile(Test2Parser.OperatorWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#operatorWhile}.
	 * @param ctx the parse tree
	 */
	void exitOperatorWhile(Test2Parser.OperatorWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(Test2Parser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(Test2Parser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#conditionOdd}.
	 * @param ctx the parse tree
	 */
	void enterConditionOdd(Test2Parser.ConditionOddContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#conditionOdd}.
	 * @param ctx the parse tree
	 */
	void exitConditionOdd(Test2Parser.ConditionOddContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#conditionComparison}.
	 * @param ctx the parse tree
	 */
	void enterConditionComparison(Test2Parser.ConditionComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#conditionComparison}.
	 * @param ctx the parse tree
	 */
	void exitConditionComparison(Test2Parser.ConditionComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(Test2Parser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(Test2Parser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Test2Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Test2Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#exprSign}.
	 * @param ctx the parse tree
	 */
	void enterExprSign(Test2Parser.ExprSignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#exprSign}.
	 * @param ctx the parse tree
	 */
	void exitExprSign(Test2Parser.ExprSignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#summand}.
	 * @param ctx the parse tree
	 */
	void enterSummand(Test2Parser.SummandContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#summand}.
	 * @param ctx the parse tree
	 */
	void exitSummand(Test2Parser.SummandContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#multSign}.
	 * @param ctx the parse tree
	 */
	void enterMultSign(Test2Parser.MultSignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#multSign}.
	 * @param ctx the parse tree
	 */
	void exitMultSign(Test2Parser.MultSignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#multiplier}.
	 * @param ctx the parse tree
	 */
	void enterMultiplier(Test2Parser.MultiplierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#multiplier}.
	 * @param ctx the parse tree
	 */
	void exitMultiplier(Test2Parser.MultiplierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#multiplierId}.
	 * @param ctx the parse tree
	 */
	void enterMultiplierId(Test2Parser.MultiplierIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#multiplierId}.
	 * @param ctx the parse tree
	 */
	void exitMultiplierId(Test2Parser.MultiplierIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#multiplierNumber}.
	 * @param ctx the parse tree
	 */
	void enterMultiplierNumber(Test2Parser.MultiplierNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#multiplierNumber}.
	 * @param ctx the parse tree
	 */
	void exitMultiplierNumber(Test2Parser.MultiplierNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Test2Parser#multiplierExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplierExpression(Test2Parser.MultiplierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Test2Parser#multiplierExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplierExpression(Test2Parser.MultiplierExpressionContext ctx);
}
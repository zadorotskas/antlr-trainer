// Generated from C:/Users/zador/IdeaProjects/antlr-trainer/src/test/resources/grammar/parser\Test2.g4 by ANTLR 4.10.1
package ru.spbstu.icc.kspt.generator.grammar.generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Test2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Test2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Test2Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(Test2Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Test2Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#constDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDeclaration(Test2Parser.ConstDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#constAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstAssign(Test2Parser.ConstAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(Test2Parser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#varAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssign(Test2Parser.VarAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure(Test2Parser.ProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(Test2Parser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operatorAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorAssign(Test2Parser.OperatorAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operatorCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorCall(Test2Parser.OperatorCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operatorBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorBegin(Test2Parser.OperatorBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operatorIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorIf(Test2Parser.OperatorIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#operatorWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorWhile(Test2Parser.OperatorWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(Test2Parser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#conditionOdd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionOdd(Test2Parser.ConditionOddContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#conditionComparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionComparison(Test2Parser.ConditionComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(Test2Parser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Test2Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#exprSign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSign(Test2Parser.ExprSignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#summand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSummand(Test2Parser.SummandContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#multSign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultSign(Test2Parser.MultSignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#multiplier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplier(Test2Parser.MultiplierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#multiplierId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplierId(Test2Parser.MultiplierIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#multiplierNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplierNumber(Test2Parser.MultiplierNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Test2Parser#multiplierExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplierExpression(Test2Parser.MultiplierExpressionContext ctx);
}
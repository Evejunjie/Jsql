package org.junjie.jsql.curd.fulfil;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import org.junjie.jsql.JSql;
import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlSelectField;
import org.junjie.jsql.curd.SqlTable;
import org.junjie.jsql.curd.standard.Query;
import org.junjie.jsql.curd.standard.SqlCase;
import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.field.GroupByList;
import org.junjie.jsql.field.OrderByList;
import org.junjie.jsql.field.SelectField;
import org.junjie.jsql.field.SelectFieldFunction;
import org.junjie.jsql.field.SelectFieldList;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlHaving;
import org.junjie.jsql.fragment.keyword.AbstractSqlHaving;
import org.junjie.jsql.fragment.keyword.SqlLikeFragment;
import org.junjie.jsql.fragment.keyword.SqlLimit;
import org.junjie.jsql.fragment.keyword.SqlWhere;
import org.junjie.jsql.fragment.keyword.Subquery;
import org.junjie.jsql.function.SqlFunction;
import org.junjie.jut.LambdaFieldFunction;

import lombok.Getter;

public abstract class AbstractQuery<E> implements Query<E, AbstractQuery<E>> {

	protected E entity;

	protected SqlTableEntity table;

	protected SqlWhere where;

	@Getter
	protected OrderByList order;

	@Getter
	protected GroupByList group;

	protected SqlLimit limit;

	protected SqlHaving having;

	/**
	 * 别名
	 */
	protected String as;
	
	
	/**
	 * 查询字段列数据
	 */
	protected SelectFieldList fieldList;
	
	protected JSql jsql;

	@Override
	public JSql getJsql() {
		return jsql;
	}
	
	public AbstractQuery(SqlTableEntity table,JSql jsql) {
		super();
		this.table = table;
		this.jsql = jsql;
		this.where = new SqlWhere();
		this.fieldList = new SelectFieldList(table);
		this.order = new OrderByList();
		this.group = new GroupByList();
	}

	@Override
	public Class<E> getEclass() {
		return (Class<E>) table.getEclass();
	}
	
	@Override
	public List<SqlField> getFieldList() {
		return fieldList.getSelect();
	}
	
	

	@Override
	public String getTableName() {
		return table.getTableName();
	}

	@Override
	public String as() {
		return as;
	}

	@Override
	public SqlTable as(String value) {
		this.as = value;
		return this;
	}

	
	@Override
	public AbstractQuery<E> entity(E entity) {
		this.entity = entity;
		return this;
	}

	@Override
	public E entity() {
		return entity;
	}

	@Override
	public <R> AbstractQuery<E> eq(LambdaFieldFunction<E, R> field, R value) {
		if (value == null) {
			where.isNull(table.getField(field));
		} else {
			where.eq(table.getField(field), value);
		}
		return this;
	}

	@Override
	public <R> AbstractQuery<E> ne(LambdaFieldFunction<E, R> field, R value) {
		if (value == null) {
			where.isNotNull(table.getField(field));
		} else {
			where.ne(table.getField(field), value);
		}
		return this;
	}

	@Override
	public <R> AbstractQuery<E> ge(LambdaFieldFunction<E, R> field, R value) {
		where.ge(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> le(LambdaFieldFunction<E, R> field, R value) {
		where.le(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> gt(LambdaFieldFunction<E, R> field, R value) {
		where.gt(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> lt(LambdaFieldFunction<E, R> field, R value) {
		where.lt(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> likeAll(LambdaFieldFunction<E, R> field, CharSequence value) {
		where.likeAll(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> like(LambdaFieldFunction<E, R> field, CharSequence value) {
		where.like(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> likeLift(LambdaFieldFunction<E, R> field, CharSequence value) {
		SqlLikeFragment like = where.like(table.getField(field));
		like.left(value.toString());
		return this;
	}

	@Override
	public <R> AbstractQuery<E> likeRight(LambdaFieldFunction<E, R> field, CharSequence value) {
		SqlLikeFragment like = where.like(table.getField(field));
		like.right(value.toString());
		return this;
	}

	@Override
	public <R> AbstractQuery<E> in(LambdaFieldFunction<E, R> field, Collection<Object> value) {
		where.in(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> notIn(LambdaFieldFunction<E, R> field, Collection<Object> value) {
		where.notIn(table.getField(field), value);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> between(LambdaFieldFunction<E, R> field, R state, R end) {
		where.between(table.getField(field), state, end);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> notBetween(LambdaFieldFunction<E, R> field, R state, R end) {
		where.notBetween(table.getField(field), state, end);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> and() {
		where.and();
		return this;
	}

	@Override
	public <R> AbstractQuery<E> or() {
		where.or();
		return this;
	}

	@Override
	public Subquery<E> and(Consumer<Subquery<E>> filter) {
		Subquery<E> subquery = new Subquery<>(table);
		subquery.entity(entity);
		filter.accept(subquery);
		where.and(subquery);
		return subquery;
	}

	@Override
	public Subquery<E> or(Consumer<Subquery<E>> filter) {
		Subquery<E> subquery = new Subquery<>(table);
		subquery.entity(entity);
		filter.accept(subquery);
		where.or(subquery);
		return subquery;
	}

	@Override
	public <R> SqlCase CASE(LambdaFieldFunction<E, R> field) {
		return CASE(field.apply(entity));
	}

	@Override
	public SqlCase CASE(SqlFragment fragment) {
		return where.CASE(fragment);
	}

	@Override
	public <R> SelectFieldFunction countBig(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.count_big(), table.getField(field));
	}

	@Override
	public <R> SelectFieldFunction groupingId(LambdaFieldFunction<E, R> field) {

		return selectFunction(SqlFunction.grouping_id(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction grouping(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.grouping(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction count(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.count(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction first(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.first(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction last(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.last(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction max(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.max(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction min(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.min(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction sum(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sum(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction avg(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.avg(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction ucase(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.ucase(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction lcase(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.lcase(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction mid(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.mid(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction stdev(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.stdev(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction stdevp(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.stdevp(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction varp(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.varp(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction abs(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.abs(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction acos(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.acos(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction asin(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.asin(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction atan(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.atan(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction atn2() {
		return selectFunction(SqlFunction.atn2(), null);

	}

	@Override
	public <R> SelectFieldFunction ceiling(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.ceiling(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction cos(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.cos(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction cot(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.cot(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction degrees(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.degrees(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction exp(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.exp(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction floor(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.floor(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction log(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.log(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction log10(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.log10(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction pi() {
		return selectFunction(SqlFunction.pi(), null);

	}

	@Override
	public <R> SelectFieldFunction power(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.power(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction radians(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.radians(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction rand(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.rand(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction round(LambdaFieldFunction<E, R> field, int length) {
		return selectFunction(SqlFunction.round(table.getField(field), length), null);

	}

	@Override
	public <R> SelectFieldFunction sign(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sign(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction sin(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sin(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction sqrt(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sqrt(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction square(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.square(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction tan(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.tan(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction ascii(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.ascii(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction CHAR(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.CHAR(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction charindex(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.charindex(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction concat(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.concat(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction concatWs(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.concat_ws(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction difference(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.difference(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction format(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.format(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction length(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.length(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction left(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.left(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction ltrim(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.ltrim(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction lower(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.lower(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction nchar(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.nchar(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction quotename(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.quotename(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction replace(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.replace(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction replicate(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.replicate(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction reverse(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.reverse(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction right(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.right(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction rtrim(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.rtrim(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction patindex(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.patindex(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction soundex(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.soundex(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction space(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.space(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction str(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.str(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction stringEscape(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.string_escape(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction stringSplit(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.string_split(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction stuff(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.stuff(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction substring(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.substring(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction translate(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.translate(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction trim(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.trim(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction unicode(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.unicode(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction upper(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.upper(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction now() {
		return selectFunction(SqlFunction.now(), null);

	}

	@Override
	public <R> SelectFieldFunction sysdatetime(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sysdatetime(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction sysdatetimeoffset(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sysdatetimeoffset(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction sysutcdatetime(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.sysutcdatetime(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction getdate(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.getdate(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction getutcdate(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.getutcdate(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction datename(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.datename(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction datepart(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.datepart(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction day(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.day(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction month(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.month(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction year(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.year(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction var(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.var(), (table.getField(field)));
	}

	@Override
	public <R> SelectFieldFunction dateFromparts(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.date_fromparts(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction datediff(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.datediff(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction datediffBig(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.datediff_big(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction dateadd(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.dateadd(), (table.getField(field)));

	}

	@Override
	public <R> SelectFieldFunction eomonth(LambdaFieldFunction<E, R> field) {
		return selectFunction(SqlFunction.eomonth(), (table.getField(field)));
	}

	@Override
	public <R> AbstractQuery<E> group(LambdaFieldFunction<E, R> field) {
		return group(table.getField(field));
	}

	@Override
	public <R> AbstractQuery<E> group(SqlField field) {
		this.group.add(field);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> orderDesc(LambdaFieldFunction<E, R> field) {
		order.asc(table.getField(field));
		return this;
	}

	@Override
	public <R> AbstractQuery<E> orderAsc(LambdaFieldFunction<E, R> field) {
		order.asc(table.getField(field));
		return this;
	}

	@Override
	public AbstractQuery<E> limit(long start, long row) {
		this.limit = SqlLimit.of(start, row);
		return this;
	}

	@Override
	public AbstractQuery<E> limit(long row) {
		this.limit = SqlLimit.of(row);
		return this;
	}

	@Override
	public <R> AbstractQuery<E> select(LambdaFieldFunction<E, R>... field) {
		for (LambdaFieldFunction<E, R> lambdaFieldFunction : field) {
			select(lambdaFieldFunction);
		}
		return this;
	}

	@Override
	public <R> AbstractQuery<E> select(Collection<LambdaFieldFunction<E, R>> field) {
		for (LambdaFieldFunction<E, R> lambdaFieldFunction : field) {
			select(lambdaFieldFunction);
		}
		return this;
	}

	public SelectFieldFunction selectFunction(SqlFunction function, SqlField field) {
		SelectFieldFunction of = SelectFieldFunction.of(SelectField.of(this, field), function);
		if (field != null) {
			function.param(field);
		}
		fieldList.select(of);
		return of;
	}

	@Override
	public <R> SqlSelectField select(LambdaFieldFunction<E, R> field) {
		SqlSelectField selectField = table.getSelectField(field,this);
		select(selectField);
		return selectField;
	}

	public <R> SqlField select(SqlField field) {
		fieldList.select(field);
		return field;
	}

	@Override
	public AbstractQuery<E> having(Consumer<SqlHaving> filter) {
		if (having == null) {
			having = new AbstractSqlHaving();
		}
		filter.accept(having);
		return this;
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql(SQLCode.SELECT);
		deposit.write(fieldList);
		deposit.writeSql(SQLCode.FROM);
		deposit.writeSqlBackquote(table.getTableName());
		if(as!=null) {
			deposit.writeAS(as);
		}
		if (!where.isEmpty()) {
			deposit.writeSql(SQLCode.WHERE);
			deposit.write(where);
		}
		deposit.write(group);
		if (having != null && !having.isEmpty()) {
			deposit.writeSql(SQLCode.HAVING);
			deposit.write(having);
		}
		deposit.write(order);
		if (limit != null) {
			deposit.write(limit);
		}
	}
}

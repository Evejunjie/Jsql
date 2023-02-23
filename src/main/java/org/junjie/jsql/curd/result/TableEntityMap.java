package org.junjie.jsql.curd.result;

public interface TableEntityMap {

	public <E> E get(Class<E> calss);
	
	public interface TableEntityMap2<A, B> extends TableEntityMap {
		public A get1();

		public B get2();
	}

	public interface TableEntityMap3<A, B, C> {
		public A get1();

		public B get2();

		public C get3();
	}

	public interface TableEntityMap4<A, B, C, D> {
		public A get1();

		public B get2();

		public C get3();

		public D get4();
	}

	public interface TableEntityMap5<A, B, C, D, E> {
		public A get1();

		public B get2();

		public C get3();

		public D get4();

		public E get5();
	}


}

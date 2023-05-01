package com.example.domain;

/**
 * @author yoshida_yuta
 *
 */
public enum SortType {

	NAME(1) {
		@Override
		public String getName() {
			return "name";
		}
	},
	DESCNAME(2) {
		@Override
		public String getName() {
			return "name DESC";
		}
	},
	PRICEM(3) {
		@Override
		public String getName() {
			return "price_m";
		}
	},
	DESCPRICEM(4) {
		@Override
		public String getName() {
			return "price_m DESC";
		}
	},
	PRICEL(5) {
		@Override
		public String getName() {
			return "price_l";
		}
	},
	DESCPRICEL(6) {
		@Override
		public String getName() {
			return "price_l DESC";
		}
	},
	OTHER(0) {
		@Override
		public String getName() {
			return "id";
		}
	};

	private final int num;

	SortType(int num) {
		this.num = num;
	}

	public abstract String getName();

	public static SortType getSortTypeByNum(int num) {
		for (SortType f : SortType.values()) {
			if (f.num == num) {
				return f;
			}
		}
		throw new IllegalArgumentException("Invalid num: " + num);
	}
}

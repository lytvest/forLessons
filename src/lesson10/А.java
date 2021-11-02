package lesson10;

class Q {
    private String field = "class A";

    public String getField() {
        return field;
    }
}

class W {
    Q q;

    public W(Q q) {
        this.q = q;
    }
    public void print(){
        System.out.println(q.getField());
    }
}


class O {
    int field = 90;
}

class F {
    private O o;

    public F() {
        this.o = new O();
    }

    public void print(){
        System.out.println("F contains o " + o.field);
    }
}
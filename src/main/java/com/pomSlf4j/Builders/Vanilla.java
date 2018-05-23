package com.pomSlf4j.Builders;

/**
 * Created by buckl on 19/02/2018.
 *
 * Want to play with builder model (get the interface/abstract from javafx.util.Builder)
 * ONLY allow the main object to be created via builder
 * INSIST that builder is created with the mandatory info needed for object
 * OTHER stuff can be added (or not) as appropriate
 *
 * If builder returns itself then can chain the sets in to long fluent list
 *
 */
public class Vanilla {

    // a bld object can ONLY be constructed using a Bldr object
    // By defining a specific constructor we block anyone using a default constructor
    //
    // ?? There was an article that went through preventing people using reflection or similar to do their own constructing
    //    but what the hell was it ??
    //


    // One condtructor & only we can use it.
    private Vanilla(VanillaBuilder bldr){
        this.name=bldr.name;
        this.job=bldr.job;
    }

    // Nott looking at earth shattering complexity here
    String name,job;
    public String getName(){
        return this.name;
    }
    public String getJob(){
        return this.job;
    }

    // May need to revisit this amd establish what the builder should be looking like
    public static class VanillaBuilder {
        // Local builder versions that we'll use when creating the real thing
        String name,job;
        public VanillaBuilder(String name){
            this.name=name;
        }

//        @Override
        public Vanilla type(String type) {
            return null;
        }

        public VanillaBuilder job(String job){
            this.job=job;
            return this;
        }

        public Vanilla build() {
            return new Vanilla(this);
        }

    }

    /* Can't really recall what this actually helped with, if anything.  Websites all use above approach ^^^^
    public abstract static class Builder<B extends Builder<B>>{
        public abstract B type(String type);
    }
    // Use ready defined builder template (Builder<T>)
    public static class BldrImpl implements Builder<bld> {
        @Override
        public bld type(String type) {
            return null;
        }
    }
    */


}

package com.pomSlf4j;

import javafx.util.Builder;

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
public class bld{

    // a bld object can ONLY be constructed using a Bldr object
    private bld(Bldr bldr){
        this.name=bldr.name;
        this.job=bldr.job;
    }

    String name,job;
    public String getName(){
        return this.name;
    }
    public String getJob(){
        return this.job;
    }

    // May need to revisit this amd establish what the builder should be looking like
    public static class Bldr {
        String name,job;
        public bld build() {
            return new bld(this);
        }
        public Bldr(String name){
            this.name=name;
        }

//        @Override
        public bld type(String type) {
            return null;
        }

        public Bldr job(String job){
            this.job=job;
            return this;
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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: types.proto

package com.sixt.rental.demo.events;

public interface AuthorOrBuilder extends
    // @@protoc_insertion_point(interface_extends:rental.Author)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.rental.Identifier identifier = 1;</code>
   */
  boolean hasIdentifier();
  /**
   * <code>.rental.Identifier identifier = 1;</code>
   */
  com.sixt.rental.demo.events.Identifier getIdentifier();
  /**
   * <code>.rental.Identifier identifier = 1;</code>
   */
  com.sixt.rental.demo.events.IdentifierOrBuilder getIdentifierOrBuilder();

  /**
   * <code>string first_name = 2;</code>
   */
  java.lang.String getFirstName();
  /**
   * <code>string first_name = 2;</code>
   */
  com.google.protobuf.ByteString
      getFirstNameBytes();

  /**
   * <code>string last_name = 3;</code>
   */
  java.lang.String getLastName();
  /**
   * <code>string last_name = 3;</code>
   */
  com.google.protobuf.ByteString
      getLastNameBytes();
}

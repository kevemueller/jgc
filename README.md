# jgc
Java library to read GnuCash files

This is a proof of concept project to read files produced by [GnuCash](https://gnucash.org). The library features
* an XML Schema definition for XML files,
* a DAO layer (Interfaces and Enums),
* implementations of the DAO layer usind JDO as well as JAXB.

## Challenges
### No official Schema definition
While XML is the primary file format for GnuCash an official Schema definition was not developed and the schema had to be reverse-engineered from XML 
files created by GnuCash.
The schema definition is straight forward except for some corner cases. The produced document features no namespace qualified elements that 
are mixed sometimes in the empty namespace, sometimes in a default namespace.
Real life examples of documents feature inconsistent ordering of elements.
The slot/value element uses Co-occurence based on a distinguishing attribute value, this feature cannot be precisely mapped to XML Schema. 
An element name based approach would have been more beneficial.

### DAO interface layer
The XML represenation and the DB representation differ slightly in where objects are "owned", but are reconcile-able.

### JDO
No major obstacles were observed.
The DB representation features containment of slots within slots that could not be mapped precisely to JDO terms.

### JAXB
Major obstacles were observed. The aim was to fully automate the DAO layer implementation classes based on the XML Schema defition.
Some choices observed by GnuCash are hard to map with JAXB. JAXB as well as its code generator (xjc) also have severe shortcomings which 
can only be partially worked around with existing tools [Maven jaxb2 plugin](https://github.com/highsource/maven-jaxb2-plugin).
#### xjc can generate object tree references only for the built-in types xs:id/xs:idref
As the references used in the document did not fit into the value space of xs:id, as custom type had to be used, hence  references had to be 
manually tagged in the bindings file.
#### JAXB does not handle binding multiple IDs for an element.
Repeated use of XmlID in the same element will result in all pieces of the compound key being bound, not the tuple. 
A custom IDResolver had to be developed that can combine multiple Ids to a compound key.
#### JAXB can only resolve String references
When resolving a reference JAXB will use only the textual content of the referring element. No converters/adapters can be plugged/will be executed. 
For non-string references this will not work. A custom XmlAdapter had to be developed to combine the parts of the key and resolve them.
#### xjc cannot use xs:key to map to IDREF
As xs:idref could not be used, all instances of a referenceType had to be manually annotated in the bindings file.
#### xjc can plug XmlAdapter only on simpleType. 
Several complexTypes needed XmlAdapters, they had to be manually plugged in the bindings file.
#### xjc cannot remove unnecessary hierarchy
The documents feature unnecessary nesting which had to be removed manually with (Un-)wrapping classes.

## Conclusion
The proof of concept is successful. The library is principally useable with both underlying document representation formats.
Due to the shortcomings of JAXB a different approach is adviseable.
* Validation of the source XML
* Transformation of the XML into a friendlier structure
* Unmarshalling
* manipulation
* Marshalling
* Transformation of the friendlier structure to the original XML
* Optional re-validation

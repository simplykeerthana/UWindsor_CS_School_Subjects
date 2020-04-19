CXXFLAGS=-std=c++17 -Wall -Wextra -Wold-style-cast
SOURCES=a3.cxx provided-signal.cxx
DEPS=$(SOURCES:.cxx=.deps)
OBJECTS=$(SOURCES:.cxx=.o)

all: a3.exe

include $(DEPS)

clean:
	rm -vf *.exe *.o *.deps

%.deps: %.cxx
	@set -e ; \
	rm -f $@ ; \
	$(CXX) -MM $(CXXFLAGS) $< >$@.$$$$ ; \
	sed 's,\($*\)\.o[ :]*,\1.o $@ : ,g' <$@.$$$$ >$@ ;\
	rm -f $@.$$$$

%.o: %.cxx %.deps
	$(CXX) $(CXXFLAGS) -c -o $@ $<

%.exe: $(OBJECTS) $(DEPS)
	$(CXX) $(CXXFLAGS) -o $@ $(OBJECTS)

